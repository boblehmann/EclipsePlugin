package org.hpccsystems.eclide.editors;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.hpccsystems.eclide.Activator;
import org.hpccsystems.eclide.ui.viewer.BrowserEx;
import org.hpccsystems.eclide.ui.viewer.TableEx;
import org.hpccsystems.eclide.ui.viewer.platform.TreeItemOwner;
import org.hpccsystems.eclide.ui.viewer.platform.WorkunitTreeItem;
import org.hpccsystems.internal.data.Data;
import org.hpccsystems.internal.data.Platform;
import org.hpccsystems.internal.data.Workunit;
import org.hpccsystems.internal.ui.tree.TreeItem;

import org.eclipse.swt.custom.SashForm;

public class ECLWindow extends MultiPageEditorPart implements IResourceChangeListener, Observer {

	private ECLEditor editor;
	
	class CWorkunitTabItem extends CTabItem implements TreeItemOwner{
	
		Workunit wu;
		WorkunitTreeItem item;
		
		SashForm sashForm;
		WorkunitViewer workunitViewer;
		BrowserEx browser;
		TableEx table;

		public CWorkunitTabItem(final CTabFolder parent, int style, int index, Workunit wu) {
			super(parent, style, index);
			this.wu = wu;
			
			item = new WorkunitTreeItem(this, null, wu);

			/*
			parent.setLayout(new FillLayout());
			container = new CTabFolder(parent, SWT.BOTTOM | SWT.FLAT);
			container.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					int newPageIndex = container.indexOf((CTabItem) e.item);
					myPageChange(newPageIndex);
				}
			});
			setControl(container);
			*/
			
			parent.setLayout(new FillLayout());
			sashForm = new SashForm(parent, SWT.HORIZONTAL);

			workunitViewer = new WorkunitViewer(wu);
			workunitViewer.createPartControl(sashForm);

			browser = new BrowserEx(sashForm);
			workunitViewer.setBrowser(browser);
		
			setText(item.getText());
			setImage(item.getImage());

			int[] weights = new int[2];
			weights[0] = 15;
			weights[1] = 85;
			sashForm.setWeights(new int[] {15, 85});
		    setControl(sashForm);
		}

		@Override
		public void update(Object element, String[] properties) {
			Display.getDefault().asyncExec(new Runnable() {   
				public void run() {
					setText(item.getText());
					setImage(item.getImage());
				}
			});
		}

		@Override
		public void refresh(Object element) {
			Display.getDefault().asyncExec(new Runnable() {   
				public void run() {
				}
			});
		}
	}

	public ECLWindow() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	CWorkunitTabItem createItem(Control control, Workunit wu) {
		int index = getPageCount();
		return createItem(index, control, wu);
	}

	CWorkunitTabItem createItem(int index, Control control, Workunit wu) {
		CTabFolder folder = (CTabFolder)getContainer();
		CWorkunitTabItem item = new CWorkunitTabItem(folder, SWT.NONE, index, wu);
		//item.setControl(control);
		return item;
	}
	
	void createEditorPage() {
		try {
			editor = new ECLEditor();
			int index = addPage(editor, getEditorInput());
			this.setTitle(editor.getTitle());
			setPageText(index, "ECL");
			setPageImage(index, Activator.getImage("icons/releng_gears.gif"));
		} catch (PartInitException e) {
			ErrorDialog.openError(
					getSite().getShell(),
					"Error creating nested text editor",
					null,
					e.getStatus());
		}
	}
	
	void createWorkunitPage(Workunit wu, boolean addToEnd) {
		WorkunitTreeItem treeItem = new WorkunitTreeItem(null, null, wu);

		//  TODO need to do better check than label...
    	boolean found = false;
    	for (int i = 1; i < getPageCount(); ++i) {
    		if (getPageText(i).compareTo(treeItem.getText()) == 0) {
    			found = true;
    			break;
    		}			    		
    	}
    	
    	if (!found) {
    		CWorkunitTabItem tabItem = addToEnd ? createItem(getContainer(), wu) : createItem(1, getContainer(), wu);
    	}
	}

	void createWorkunitPages() {
		update(null, new Boolean(true));
	}

	protected void createPages() {
		createEditorPage();
		createWorkunitPages();
//		createPage1();
//		createPage2();
	}
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		CTabItem childItem = ((CTabFolder)getContainer()).getItem(newPageIndex);
		if (childItem != null && childItem instanceof CWorkunitTabItem) {
			CWorkunitTabItem treeItemTabItem = (CWorkunitTabItem)childItem;
			//treeItemTabItem.loadChildren();					
		}
	}
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		final boolean addToEnd = (arg instanceof Boolean) ? (Boolean)arg : false;
		Data data = Data.get();
		for (final Platform p : data.getPlatforms()) {
			p.addObserver(this);
			for(final Workunit w : p.getWorkunits()) {
				String path = w.getApplicationValue("path");
	
			    IFileEditorInput input = (IFileEditorInput) getEditorInput(); 
			    IFile file = input.getFile();
			    String path2 = file.getFullPath().toPortableString();
	
			    if (path.compareTo(path2) == 0) {
			    	Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
					    	createWorkunitPage(w, addToEnd);
						}
			    	});
			    }
			}
		}
	}
}

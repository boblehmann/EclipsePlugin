<<<<<<< HEAD
/*******************************************************************************
 * Copyright (c) 2007 Ryan Rusaw
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ryan Rusaw
 *******************************************************************************/
package org.seisint.eclide.ast;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

public class OctaveTranslationUnit extends ModuleDeclaration {
	
	public OctaveTranslationUnit(OctaveASTListNode stmts) {
		super(stmts.sourceEnd() - stmts.sourceStart(), false);
		setStatements(stmts.getChilds());
	}
	
}
=======
/*******************************************************************************
 * Copyright (c) 2007 Ryan Rusaw
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Ryan Rusaw
 *******************************************************************************/
package org.seisint.eclide.ast;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

public class OctaveTranslationUnit extends ModuleDeclaration {
	
	public OctaveTranslationUnit(OctaveASTListNode stmts) {
		super(stmts.sourceEnd() - stmts.sourceStart(), false);
		setStatements(stmts.getChilds());
	}
	
}
>>>>>>> 491f10a275eb44f4aa5f7f631ff2004eba623b33

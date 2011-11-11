/*##############################################################################

    Copyright (C) 2011 HPCC Systems.

    All rights reserved. This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
############################################################################## */

package org.hpccsystems.internal;

import java.io.BufferedReader; 
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ECLArchiveParser {

	private SAXParserFactory parserFactory;
	public Set<IFile> ancestors;

	class ECLArchiveHandler extends StackHandler {
		IFile file;

		ECLArchiveHandler(IFile file) {
			super();
			this.file = file;
			ancestors = new HashSet<IFile>();
		}

		@Override
		public void endElement(String uri, String localName, String qName)	throws SAXException {
			assert(!elementStack.empty());
			switch(elementStack.size()) {
			case 3:
				Element e = elementStack.peek();
				IResource resource = Eclipse.findResource(e.attributes.getValue("sourcePath"));
				if (resource != null && resource instanceof IFile && !resource.equals(file) && resource.getName().endsWith(".ecl")) { 
					ancestors.add((IFile) resource);
				}
				
				break;
			default:
				break;
			}
			super.endElement(uri, localName, qName);
		}
	}

	public ECLArchiveParser(IFile file, BufferedReader reader) {
		parserFactory = SAXParserFactory.newInstance();

		SAXParser parser = null;
		parserFactory.setValidating(false);

		try {
			parser = parserFactory.newSAXParser();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		ECLArchiveHandler handler = new ECLArchiveHandler(file); 
		try {
			parser.parse(new InputSource(reader), handler);
		} catch (SAXException e) {
			//  If there is an error we may end up here. 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
}

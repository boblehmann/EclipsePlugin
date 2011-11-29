/*******************************************************************************
 * Copyright (c) 2011 HPCC Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     HPCC Systems - initial API and implementation
 ******************************************************************************/
package org.hpccsystems.eclide.text;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

public class ECLWordRule implements IRule {


	protected static final int UNDEFINED= -1;

	/** The word detector used by this rule. */
	protected IWordDetector fDetector;
	/** The default token to be returned on success and if nothing else has been specified. */
	protected IToken fDefaultToken;
	/** The column constraint. */
	protected int fColumn= UNDEFINED;
	/** The table of predefined words and token for this rule. */
	protected Map<String, IToken> fWords= new HashMap<String, IToken>();
	/** Buffer used for pattern detection. */
	private StringBuffer fBuffer= new StringBuffer();
	/**
	 * Tells whether this rule is case sensitive.
	 * @since 3.3
	 */
	private boolean fIgnoreCase= false;


	/**
	 * Creates a rule which, with the help of an word detector, will return the token
	 * associated with the detected word. If no token has been associated, the scanner
	 * will be rolled back and an undefined token will be returned in order to allow
	 * any subsequent rules to analyze the characters.
	 *
	 * @param detector the word detector to be used by this rule, may not be <code>null</code>
	 * @see #addWord(String, IToken)
	 */
	public ECLWordRule(IWordDetector detector) {
		this(detector, Token.UNDEFINED, false);
	}

	/**
	 * Creates a rule which, with the help of a word detector, will return the token
	 * associated with the detected word. If no token has been associated, the
	 * specified default token will be returned.
	 *
	 * @param detector the word detector to be used by this rule, may not be <code>null</code>
	 * @param defaultToken the default token to be returned on success
	 *			if nothing else is specified, may not be <code>null</code>
	 * @see #addWord(String, IToken)
	 */
	public ECLWordRule(IWordDetector detector, IToken defaultToken) {
		this(detector, defaultToken, false);
	}

	/**
	 * Creates a rule which, with the help of a word detector, will return the token
	 * associated with the detected word. If no token has been associated, the
	 * specified default token will be returned.
	 *
	 * @param detector the word detector to be used by this rule, may not be <code>null</code>
	 * @param defaultToken the default token to be returned on success
	 *			if nothing else is specified, may not be <code>null</code>
	 * @param ignoreCase the case sensitivity associated with this rule
	 * @see #addWord(String, IToken)
	 * @since 3.3
	 */
	public ECLWordRule(IWordDetector detector, IToken defaultToken, boolean ignoreCase) {
		Assert.isNotNull(detector);
		Assert.isNotNull(defaultToken);

		fDetector= detector;
		fDefaultToken= defaultToken;
		fIgnoreCase= ignoreCase;
	}

	/**
	 * Adds a word and the token to be returned if it is detected.
	 *
	 * @param word the word this rule will search for, may not be <code>null</code>
	 * @param token the token to be returned if the word has been found, may not be <code>null</code>
	 */
	public void addWord(String word, IToken token) {
		Assert.isNotNull(word);
		Assert.isNotNull(token);

		// If case-insensitive, convert to lower case before adding to the map
		if (fIgnoreCase)
			word= word.toLowerCase();
		fWords.put(word, token);
	}

	/**
	 * Sets a column constraint for this rule. If set, the rule's token
	 * will only be returned if the pattern is detected starting at the
	 * specified column. If the column is smaller then 0, the column
	 * constraint is considered removed.
	 *
	 * @param column the column in which the pattern starts
	 */
	public void setColumnConstraint(int column) {
		if (column < 0)
			column= UNDEFINED;
		fColumn= column;
	}
	
	/*
	 * @see IRule#evaluate(ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {
		int c= scanner.read();
		if (c != ICharacterScanner.EOF && fDetector.isWordStart((char) c)) {
			if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {

				fBuffer.setLength(0);
				do {
					fBuffer.append((char) c);
					c= scanner.read();
				} while (c != ICharacterScanner.EOF && fDetector.isWordPart((char) c));
				scanner.unread();

				String buffer= fBuffer.toString();
				// If case-insensitive, convert to lower case before accessing the map
				if (fIgnoreCase)
					buffer= buffer.toLowerCase();
				
				IToken token= (IToken)fWords.get(buffer);

				if (token != null)
					return token;

				int i = buffer.length() - 1;
				while(i >= 0 && (Character.isDigit(buffer.charAt(i)) || buffer.charAt(i) == '_'))
					--i;

				String s2 = new String(buffer.getBytes(), 0, i + 1);
				token = (IToken)fWords.get(s2);

				if (token != null)
					return token;

				if (fDefaultToken.isUndefined())
					unreadBuffer(scanner);

				return fDefaultToken;
			}
		}

		scanner.unread();
		return Token.UNDEFINED;
	}

	/**
	 * Returns the characters in the buffer to the scanner.
	 *
	 * @param scanner the scanner to be used
	 */
	protected void unreadBuffer(ICharacterScanner scanner) {
		for (int i= fBuffer.length() - 1; i >= 0; i--)
			scanner.unread();
	}

}

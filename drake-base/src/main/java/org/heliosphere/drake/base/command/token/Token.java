/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project artefact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.heliosphere.drake.base.command.token;

import java.util.ArrayList;
import java.util.List;

/**
 * String tokenizer.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class Token
{
	/**
	 * Token index.
	 */
	private int index;

	/**
	 * Source string containing tokens.
	 */
	private String string;

	/**
	 * Creates a new token.
	 * <p>
	 * @param index Token index.
	 * @param string Source string to tokenize.
	 */
	public Token(final int index, final String string)
	{
		this.index = index;
		this.string = string;
	}

	/**
	 * Returns the token index.
	 * <p>
	 * @return Token index.
	 */
	public final int getIndex()
	{
		return index;
	}

	/**
	 * Returns the string to tokenize.
	 * <p>
	 * @return Source string.
	 */
	public final String getString()
	{
		return string;
	}

	@SuppressWarnings("nls")
	@Override
	public String toString()
	{
		return (string != null ? string : "(null)") + ":" + Integer.toString(index);
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Token other = (Token) obj;

		return string == null ? other.string == null : string.equals(other.string);
	}

	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 43 * hash + (string != null ? string.hashCode() : 0);
		return hash;
	}

	/**
	 * Tokenizes the input string.
	 * <p>
	 * @param input String to tokenize.
	 * @return Collection of tokens.
	 */
	@SuppressWarnings("nls")
	public static List<Token> tokenize(final String input)
	{
		List<Token> result = new ArrayList<>();
		if (input == null)
		{
			return result;
		}

		//		int WHITESPACE = 0;
		//		int WORD = 1;
		//		int STRINGDQ = 2;
		//		int STRINGSQ = 3;
		//		int COMMENT = 4;

		int state = 0;

		int tokenIndex = -1;
		StringBuilder token = new StringBuilder("");

		for (int i = 0; i < input.length(); i++)
		{
			char ch = input.charAt(i);
			switch (state)
			{
				case 0:
					if (Character.isWhitespace(ch))
					{
						continue;
					}
					if ((Character.isLetterOrDigit(ch)) || (ch == '_'))
					{
						state = 1;
						tokenIndex = i;
						token.append(ch);
					}
					else if (ch == '"')
					{
						state = 2;
						tokenIndex = i;
					}
					else if (ch == '\'')
					{
						state = 3;
						tokenIndex = i;
					}
					else
					{
						state = 1;
						tokenIndex = i;
						token.append(ch);
					}
					break;
				case 1:
					if (Character.isWhitespace(ch))
					{
						result.add(new Token(tokenIndex, token.toString()));
						token.setLength(0);
						state = 0;
					}
					else if ((Character.isLetterOrDigit(ch)) || (ch == '_'))
					{
						token.append(ch);
					}
					else if (ch == '"')
					{
						if ((i < input.length() - 1) && (input.charAt(i + 1) == '"'))
						{
							token.append('"');
							i++;
						}
						else
						{
							state = 2;
						}
					}
					else if (ch == '\'')
					{
						if ((i < input.length() - 1) && (input.charAt(i + 1) == '\''))
						{
							token.append('\'');
							i++;
						}
						else
						{
							state = 3;
						}
					}
					else if (ch == '#')
					{
						result.add(new Token(tokenIndex, token.toString()));
						token.setLength(0);
						state = 4;
					}
					else
					{
						token.append(ch);
					}
					break;
				case 2:
					if (ch == '"')
					{
						if ((i < input.length() - 1) && (input.charAt(i + 1) == '"'))
						{
							token.append('"');
							i++;
						}
						else
						{
							state = 1;
						}
					}
					else
					{
						token.append(ch);
					}

					break;
				case 3:
					if (ch == '\'')
					{
						if ((i < input.length() - 1) && (input.charAt(i + 1) == '\''))
						{
							token.append('\'');
							i++;
						}
						else
						{
							state = 1;
						}
					}
					else
					{
						token.append(ch);
					}

					break;
				case 4:
					break;
				default:
					throw new AssertionError("Unknown state: " + state);
			}

		}

		if ((state == 1) || (state == 2) || (state == 3))
		{
			result.add(new Token(tokenIndex, token.toString()));
		}

		return result;
	}

	/**
	 * Escape string.
	 * <p>
	 * @param input The input.
	 * @return Returns the string.
	 */
	@SuppressWarnings("nls")
	public static String escapeString(final String input)
	{
		StringBuilder escaped = new StringBuilder(input.length() + 10);
		escaped.append('"');
		for (int i = 0; i < input.length(); i++)
		{
			if (input.charAt(i) == '"')
			{
				escaped.append("\"\"");
			}
			else
			{
				escaped.append(input.charAt(i));
			}
		}
		escaped.append('"');
		return escaped.toString();
	}
}

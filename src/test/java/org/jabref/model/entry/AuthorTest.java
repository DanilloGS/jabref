package org.jabref.model.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorTest {

    Author author1 = new Author("Maria", "M", null, "Silva", null);
    Author author2 = new Author("João", "J", "de", "Souza", "Jr");
    Author author3 = new Author(null, null, null, null, null);


    @Test
    void getFirstAndLastAuthorsName () {
      assertEquals("M Silva", author1.getFirstLast(true));
      assertEquals("Maria Silva", author1.getFirstLast(false));
      assertEquals("J de Souza, Jr", author2.getFirstLast(true));
      assertEquals("João de Souza, Jr", author2.getFirstLast(false));
      assertEquals("", author3.getFirstLast(true));
      assertEquals("", author3.getFirstLast(false));
    }

    @Test
    void getAuthorsNameForAlphabetization () {
      assertEquals("Silva, M", author1.getNameForAlphabetization());
      assertEquals("Souza, Jr, J", author2.getNameForAlphabetization());
      assertEquals("", author3.getNameForAlphabetization());
    }

    @Test
    void getLastOnly () {
        assertEquals("Silva", author1.getLastOnly());
        assertEquals("de Souza", author2.getLastOnly());
        assertEquals("", author3.hashCode());
    }

    @Test
    void latexFreeMustBeEqualOwnObject () {
      assertEquals( author1, author1.latexFree());
      assertEquals( author2, author2.latexFree());
      assertEquals( author3, author3.latexFree());
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenNull() {
      assertEquals(null, Author.addDotIfAbbreviation(null));
      assertEquals("", Author.addDotIfAbbreviation(""));
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenAllUpperCase() {
      assertEquals("J. A. B. R. E. F.", Author.addDotIfAbbreviation("JABREF"));
      assertEquals("U. N. B.", Author.addDotIfAbbreviation("UNB"));
      assertEquals("U. N. B.", Author.addDotIfAbbreviation("UNB."));
      assertEquals("U. N. B.", Author.addDotIfAbbreviation("U.NB."));
    }

  @Test
    void addDotIfAbbreviationDoNotAddDot_WhenOnlyOneLetter() {
      assertEquals("o", Author.addDotIfAbbreviation("o"));
      assertEquals("O.", Author.addDotIfAbbreviation("O"));
      assertEquals("O.", Author.addDotIfAbbreviation("O."));
      assertEquals("O. Moore", Author.addDotIfAbbreviation("O Moore"));
      assertEquals("Moore, O.", Author.addDotIfAbbreviation("Moore, O"));
      assertEquals("A. O. Moore", Author.addDotIfAbbreviation("A O Moore"));
      assertEquals("O. von Moore", Author.addDotIfAbbreviation("O von Moore"));
      assertEquals("Moore, O. and O. Moore", Author.addDotIfAbbreviation("Moore, O and O Moore"));
      assertEquals("Moore, O. and O. Moore and Moore, O. O.", Author.addDotIfAbbreviation("Moore, O and O Moore and Moore, O O"));
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenUpperAndLowerCaseOnSameString() {
      assertEquals("JabRef", Author.addDotIfAbbreviation("JabRef"));
      assertEquals("UnB", Author.addDotIfAbbreviation("UnB"));
      assertEquals("MEmre", Author.addDotIfAbbreviation("MEmre"));
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenSeparatedWithHyphen() {
      assertEquals("O.l-A.", Author.addDotIfAbbreviation("O.l-A"));
      assertEquals("U.-N.-B.", Author.addDotIfAbbreviation("U-N-B"));
      assertEquals("A.-O.", Author.addDotIfAbbreviation("A-O"));
      assertEquals("a-l-o", Author.addDotIfAbbreviation("a-l-o"));
      assertEquals("A.-O. Moore", Author.addDotIfAbbreviation("A-O Moore"));
      assertEquals("Moore, A.-O.", Author.addDotIfAbbreviation("Moore, A-O"));
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenMultipleDots() {
      assertEquals("O. L. A.", Author.addDotIfAbbreviation("O.L.A"));
      assertEquals("O. J.", Author.addDotIfAbbreviation("O.J."));
      assertEquals("A. A. A. A. A. A.", Author.addDotIfAbbreviation("A.A.A.A.A.A"));
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenAlreadyHaveDot() {
      assertEquals("Moore, A. O.", Author.addDotIfAbbreviation("Moore, A. O."));
      assertEquals("Jose M. G. de S.", Author.addDotIfAbbreviation("Jose M. G. de S."));
    }

    @Test
    void addDotIfAbbreviationDoNotAddDot_WhenHaveSpecialChars() {
      assertEquals("Moore, O, Jr", Author.addDotIfAbbreviation("Moore, O, Jr"));
      assertEquals("{\\'{E}}douard", Author.addDotIfAbbreviation("{\\'{E}}douard"));
      assertEquals("J{\\\"o}rg", Author.addDotIfAbbreviation("J{\\\"o}rg"));
    }

}

package by.vlad.jwd_task3.composite;

public enum TextComponentType {
    WORD("", ""),
    LEXEME("", " "),
    SENTENCE("", ""),
    PARAGRAPH("\t", "\n"),
    TEXT("", "");

    private final String prefix;
    private final String postfix;

    TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}

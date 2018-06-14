package rpn.event;

import rpn.tokenizer.Token;

public class TokenEvent implements Event
{
    private Token token;


    public TokenEvent(Token token)
    {
        this.token = token;
    }

    public Token getToken()
    {
        return token;
    }
}

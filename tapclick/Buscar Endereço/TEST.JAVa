private boolean step() throws AnalysisError
{
if (currentToken == null)
{
int pos = 0;
if (previousToken != null)
pos = previousToken.getPosition() +
previousToken.getLexeme().length();
currentToken = new Token(DOLLAR, "$", pos);
}
int x = ((Integer)stack.pop()).intValue();
int a = currentToken.getId();
if (x == EPSILON)
{
return false;
}
else if (isTerminal(x))
{
if (x == a)
{
if (stack.empty())
return true;
else
56
{
previousToken = currentToken;
currentToken = scanner.nextToken();
return false;
}
}
else
{
throw new SyntaticError(EXPECTED_MESSAGE[x],
currentToken.getPosition());
}
}
else if (isNonTerminal(x))
{
if (pushProduction(x, a))
return false;
else
throw new SyntaticError(ERROR_MESSAGE[x-
FIRST_NON_TERMINAL], currentToken.getPosition());
}
else if (isSemanticAction(x))
{
semanticAnalyser.executeAction(x-FIRST_SEMANTIC_ACTION,
previousToken);
return false;
}
else
return true;
}
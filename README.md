# Teste Java WS Work
 Teste para vaga de desenvolverdor Java
 
A API foi feita com Autenticação por Bearer Token por um nivel de segurança relativamente alto com uma chave de 200 caracteres para criptografia, e por não gastar recursos do sistema com dados de sessão, facil implementação e facilmente implementado por outro sistemas.
Por estar usando um banco em memória H2, o spring automaticamente adicionará um usuario para testes. e-mail: user@user.com, senha: User157!

Foi implementado um CRUD para cada entidade, e um controller para receber os dados .csv como arquivo de um form. Os Id's no .csv são ignorados para evitar sobrescrição e duplicatas dos dados já no DB.

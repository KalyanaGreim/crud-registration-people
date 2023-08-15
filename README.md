# crud-registration-people
O servidor estará disponível em http://localhost:8000.

GET /pessoas/{id}: Retorna os detalhes de uma pessoa específica com base no ID fornecido. Se a pessoa não for encontrada, retorna um erro 404.

GET /pessoas: Retorna a lista de todas as pessoas cadastradas.

POST /pessoas: Cria uma nova pessoa com base nos dados fornecidos no corpo da solicitação (PessoaRequest). Retorna a nova pessoa criada no corpo da resposta com o status 201 (Created).

PUT /pessoas/{id}: Atualiza os dados de uma pessoa existente com base no ID fornecido e nos dados fornecidos no corpo da solicitação (PessoaRequest). Retorna a pessoa atualizada no corpo da resposta.

DELETE /pessoas/{id}: Deleta uma pessoa existente com base no ID fornecido. Retorna um status 204 (No Content) se a exclusão for bem-sucedida.



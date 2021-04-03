from repositories.clients.ClientsRepository import ClientsRepository

clients_repo = ClientsRepository()
clients = clients_repo.getAllClients()
print(type(clients))
for row in clients:
    print(row, type(row))


from app.services.ClientsService import ClientsService

clients = ClientsService().get_all_clients()
clients_json = [obj.to_dict() for obj in clients]
print(clients_json)
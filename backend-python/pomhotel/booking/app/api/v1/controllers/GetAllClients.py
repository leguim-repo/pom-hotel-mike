from fastapi import APIRouter

from fastapi import APIRouter

from app.services.ClientsServices import ClientsService

router = APIRouter()


@router.get("/")
async def get_all_clients():
    clients = ClientsService().get_all_clients()
    clients_in_json = [obj.to_dict() for obj in clients]
    return {"clients": clients_in_json}

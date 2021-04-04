from fastapi import APIRouter, HTTPException

from app.services.ClientsServices import ClientsService
from app.ui.api.v1.exceptions.ApiManagerException import ApiManagerException

router = APIRouter()


@router.get("/")
async def get_all_clients():
    try:
        clients = ClientsService().get_all_clients()
        clients_in_json = [client.to_dict() for client in clients]
    except Exception as e:
        ManagerException = ApiManagerException()
        raise HTTPException(status_code=500, detail=ManagerException.error_get_all_clients(e))
    else:
        return {"clients": clients_in_json}

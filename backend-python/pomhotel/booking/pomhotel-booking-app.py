import uvicorn
from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles

from app.api.v1.controllers import HealthController, GetAllClients, LandingController, GetRandomMusicURL
from app.configuration.api_configuration import api_basename

app: FastAPI = FastAPI()
app.mount("/static", StaticFiles(directory="./app/resources/static"), name="static")

app.include_router(HealthController.router, prefix=api_basename + "/health", tags=["health"], responses={404: {"description": "Not found"}})
app.include_router(GetAllClients.router, prefix=api_basename + "/getallclients", tags=["getallclients"], responses={404: {"description": "Not found"}})
app.include_router(GetRandomMusicURL.router, prefix=api_basename + "/getrandommusicurl", tags=["getrandommusicurl"], responses={404: {"description": "Not found"}})

app.include_router(LandingController.router, responses={404: {"description": "Not found"}})

if __name__ == '__main__':
    uvicorn.run(app, host="0.0.0.0", port=8080)
    print('exit')



import uvicorn
from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from starlette.middleware.cors import CORSMiddleware

from app.configuration.api_configuration import api_basename
from app.ui.api.v1.controllers import HealthController, GetAllClients, GetRandomMusicURL, LandingController

app: FastAPI = FastAPI()

# Allow all cross origin for Dev purpose
app.add_middleware(
    CORSMiddleware,
    allow_credentials=False,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"]
)


app.include_router(HealthController.router, prefix=api_basename + "/health", tags=["health"], responses={404: {"description": "Not found"}})
app.include_router(GetAllClients.router, prefix=api_basename + "/getallclients", tags=["getallclients"], responses={404: {"description": "Not found"}})
app.include_router(GetRandomMusicURL.router, prefix=api_basename + "/music", tags=["music"], responses={404: {"description": "Not found"}})
app.include_router(LandingController.router, responses={404: {"description": "Not found"}})
app.mount("/static", StaticFiles(directory="./app/resources/static"), name="static")

if __name__ == '__main__':

    uvicorn.run(app, host="0.0.0.0", port=8080)
    print('exit')



import uvicorn
from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles

from app.ui.configuration.api_configuration import *

from app.ui.api.v1.controllers import HealthController
from app.ui.controllers import LandingController


app: FastAPI = FastAPI()
app.mount("/static", StaticFiles(directory="./app/resources/static"), name="static")

app.include_router(HealthController.router, prefix=api_basename+"/health", tags=["health"], responses={404: {"description": "Not found"}})
app.include_router(LandingController.router, responses={404: {"description": "Not found"}})

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8080)
    print('exit')

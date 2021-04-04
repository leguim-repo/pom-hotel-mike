from functools import lru_cache

from fastapi import APIRouter
from fastapi.responses import HTMLResponse
from starlette.responses import RedirectResponse

router = APIRouter()


@router.get("/")
def main_controller_view():
    result = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Landing page</title>
        <style>
        body {
          background-image: url('static/images/nasa.svg');
          background-repeat: no-repeat;
          background-attachment: fixed;  
          background-size: cover;
        }
        </style>
    </head>
    <body>
        <h1>Pom Hotel & SPA Backend Python Edition</h1>
        <p><a href="docs">Examinar API Swagger</a></p>
    
    </body>
    </html>
    """
    #return HTMLResponse(content=result)
    return RedirectResponse("http://localhost:8080/static/index.html")

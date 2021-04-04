from fastapi import APIRouter

from fastapi import APIRouter

from app.api.v1.services.RandomMusicURLService import RandomMusicURLService

router = APIRouter()


@router.get("/")
async def get_all_clients():
    url = RandomMusicURLService().getRandomMusicURL()
    return {"url": url}

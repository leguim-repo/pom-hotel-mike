from fastapi import APIRouter

from app.ui.api.v1.services.RandomMusicURLService import RandomMusicURLService

router = APIRouter()


@router.get("/")  # Enable compatibly with react frontend
async def get_Random_Music_URL():
    url = RandomMusicURLService().getRandomMusicURL()
    return {"link": url}


@router.get("/random")
async def get_Random_Music_URL():
    url = RandomMusicURLService().getRandomMusicURL()
    return {"url": url}


@router.get("/getallmusicurl")
async def get_All_Music_URL():
    url = RandomMusicURLService().getAllMusicURL()
    return {"url": url}

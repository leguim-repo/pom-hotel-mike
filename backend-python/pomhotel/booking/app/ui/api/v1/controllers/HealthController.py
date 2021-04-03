from fastapi import APIRouter, HTTPException

router = APIRouter()


@router.get("/")
async def health():
    return {"health": "is alive"}
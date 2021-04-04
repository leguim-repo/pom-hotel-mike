import random
from app.api.v1.services.RandomMusicURLServiceInterface import RandomMusicURLServiceInterface


class RandomMusicURLService(RandomMusicURLServiceInterface):
    musicURLS = [
        "https://youtu.be/mL7Gb6rcAAo", # Himno Oficial del POM Hotel Albano & Romina Power - Felicità Italiano Ragazzi Dance Remix
        "https://youtu.be/4ollgP4iCm0",  # Amelie Lens all night long @ Labyrinth Club 2017 (8 hours session)
        "https://youtu.be/GJkuTx1DQzg",  # Amelie Lens at Atomium in Brussels, Belgium for Cercle
        "https://youtu.be/Y6A_Czw8TFU",  # Amelie Lens lockdown session at home
        "https://youtu.be/_WCur8LhH8I",  # Awakenings Festival 2019 Sunday - Live set Amelie Lens @ Area Y
        "https://youtu.be/m30QEZG8aQA",  # Carl Cox Techno DJ Set Live From The Off Sonar Closing Party Barcelona
        "https://youtu.be/FIMnXw_3Rgk",  # Carl Cox live at The Brooklyn Mirage, NYC
        "https://youtu.be/vy-k0FopsmY",  # Carl Cox Boiler Room Ibiza Villa Takeovers DJ Set
        "https://youtu.be/kwKrNtq9gHI",  # Carl Cox Epic House Set From DJ Mag HQ Ibiza
        "https://youtu.be/k1d1twnD7Tk",  # Bob Sinclar live from Studio Ibiza
        "https://youtu.be/yj2bHkkBS-Q",  # Gabry Ponte - Live Felicità (di Albano & Romina Power) - (Full HD) - 31.08.2020 - Battiti live
        "https://youtu.be/A8gdkWOsc6Q",  # Gabry Ponte - Medley Live - Battiti Live - estate 2020 - 31.08.2020
        "https://youtu.be/P867JatlH18",  # Bob Sinclar - Live from Bob's Studio (Heineken powered by Defected)
    ]

    def getRandomMusicURL(self) -> str:
        url = random.choice(self.musicURLS)
        return url

    def __init__(self):
        pass

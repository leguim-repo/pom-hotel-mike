class ApiManagerException:
    def __init__(self):
        pass

    @classmethod
    def error_get_all_clients(cls, e: Exception):
        return {"Exception": str(e)}

    @classmethod
    def error_get_client_by_id(cls, clientId: int, e: Exception = None):
        if e is not None:
            return {"clientId": str(clientId),
                    "Exception": str(e)
                    }
        else:
            return {"clientId": str(clientId),
                    }

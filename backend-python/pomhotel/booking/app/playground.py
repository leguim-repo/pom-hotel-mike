class SomeAbstraction:
    pass  # lots of stuff - but missing something


class Mixin1:
    def something(self):
        pass  # one implementation


class Mixin2:
    def something(self):
        pass  # another


class Concrete1(SomeAbstraction, Mixin1):
    pass


class Concrete2(SomeAbstraction, Mixin2):
    pass

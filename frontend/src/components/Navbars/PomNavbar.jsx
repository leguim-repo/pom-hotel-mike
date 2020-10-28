import React from "react";
// reactstrap components
import {
  Collapse,
  NavbarBrand,
  Navbar,
  NavItem,
  NavLink,
  Nav,
  Container,
} from "reactstrap";


// https://demos.creative-tim.com/now-ui-kit-react/#/documentation/introduction

function PomNavbar() {
  const [navbarColor, setNavbarColor] = React.useState("navbar-transparent"); // hooks
  const [collapseOpen, setCollapseOpen] = React.useState(false);
  React.useEffect(() => {
    const updateNavbarColor = () => {
      if (
        document.documentElement.scrollTop > 399 ||
        document.body.scrollTop > 399
      ) {
        setNavbarColor("");
      } else if (
        document.documentElement.scrollTop < 400 ||
        document.body.scrollTop < 400
      ) {
        setNavbarColor("navbar-transparent");
      }
    };
    window.addEventListener("scroll", updateNavbarColor);
    return function cleanup() {
      window.removeEventListener("scroll", updateNavbarColor);
    };
  });
  
  return (
    <>
      {collapseOpen ? (
        <div
          id="bodyClick"
          onClick={() => {
            document.documentElement.classList.toggle("nav-open");
            setCollapseOpen(false);
          }}
        />
      ) : null}
      <Navbar className={"fixed-top " + navbarColor} expand="lg" color="piscina">
        <Container>
          <div className="navbar-translate">
            <NavbarBrand
              href="#"
              id="navbar-brand">
              POM Hotel & SPA
            </NavbarBrand>


            <button
              className="navbar-toggler navbar-toggler"
              onClick={() => {
                document.documentElement.classList.toggle("nav-open");
                setCollapseOpen(!collapseOpen);
              }}
              aria-expanded={collapseOpen}
              type="button">
              <span className="navbar-toggler-bar top-bar"></span>
              <span className="navbar-toggler-bar middle-bar"></span>
              <span className="navbar-toggler-bar bottom-bar"></span>
            </button>
          </div>

          <Collapse
            className="justify-content-end"
            isOpen={collapseOpen}
            navbar>
            <Nav navbar>

              <NavItem>
                <NavLink href="/"><p>Home</p></NavLink>
              </NavItem>

              <NavItem>
                <NavLink href="/rooms"><p>Rooms</p></NavLink>
              </NavItem>

              <NavItem>
                <NavLink href="/"><p>About</p></NavLink>
              </NavItem>

              <NavItem>
                <NavLink href="/login"><p>Sign In</p></NavLink>
              </NavItem>

            </Nav>
          </Collapse>
        </Container>
      </Navbar>
    </>
  );
}

export default PomNavbar;
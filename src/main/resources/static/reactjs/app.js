'use strict';

console.log(React);
console.log(ReactDOM);

const element = React.createElement("h1", null, "Que pasa peña!");
const node = document.getElementById("app");

ReactDOM.render(element, node);


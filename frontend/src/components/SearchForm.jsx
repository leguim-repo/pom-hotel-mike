import React, { useState } from "react";

const initialFilter = {
  price_from: "",
  price_to: "",
  type: "",
  guests: "",
  checkin: "",
  checkout: "",
};
const SearchForm = (props) => {
  const [filter, setFilter] = useState(initialFilter);

  function resetFilter(event) {
    setFilter(initialFilter);
  }

  function handleChange(event) {
    const newFilter = {
      ...filter,
      [event.target.name]: event.target.value,
    };
    setFilter(newFilter);
    props.onChangeFilter(newFilter);
  }

  const p = props.parametros.map((elemento) => (
                      <p>{elemento}</p>
                    ));

  return (
    <div className="filter">
      {p}
      <input
        type="number"
        placeholder="price from"
        name="price_from"
        value={filter.price_from}
        onChange={handleChange}
      />
      <input
        type="number"
        placeholder="price to"
        name="price_to"
        value={filter.price_to}
        onChange={handleChange}
      />
      <input
        type="number"
        placeholder="guest_from"
        name="guests"
        value={filter.guests}
        onChange={handleChange}
      />
      <input
        type="text"
        placeholder="type"
        name="type"
        value={filter.type}
        onChange={handleChange}
      />
      <input
        type="date"
        placeholder="checkin"
        name="checkin"
        value={filter.checkin}
        onChange={handleChange}
      />
      <input
        type="date"
        placeholder="checkout"
        name="checkout"
        value={filter.checkout}
        onChange={handleChange}
      />
      <button onClick={resetFilter}>reset</button>
    </div>
  );
};

export default SearchForm;

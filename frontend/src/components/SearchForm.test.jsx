import React from 'react';
import SearchForm from './SearchForm';
import { shallow } from 'enzyme';

describe('Search form unit test', () => {
    test('it should render without crashing' , () => {
        const wrapper = shallow(<SearchForm parametros={[]}/>);
        expect(wrapper).toMatchSnapshot(); 
    });


    test('should have required inputs', ()=>{
        const wrapper = shallow(<SearchForm parametros={[]}/>);
        expect(wrapper.find('input[name="price_from"]')).toHaveLength(1);
        expect(wrapper.find('input[name="price_to"]')).toHaveLength(1);
        expect(wrapper.find('input[name="guests"]')).toHaveLength(1);
        expect(wrapper.find('input[name="type"]')).toHaveLength(1);
        expect(wrapper.find('input[name="checkin"]')).toHaveLength(1);
        expect(wrapper.find('input[name="checkout"]')).toHaveLength(1);
    });

    test('should crash if parametros is not an array', ()=>{
        expect(()=> { shallow(<SearchForm />) }).toThrow();

    });

    test('should run onChange with the new state', ()=>{
        const updateFilter = jest.fn(); //mock de la funcion
        const wrapper = shallow(<SearchForm parametros={[]} onChangeFilter={updateFilter}/>);
        wrapper.find('input[name="price_from"]').simulate('change', {target:{value: '100', name: 'price_from'}}); // simulamos que introducimo 100 a price_from

        //comprobamos que la funcion a sido llamada con el objeto que nos interesa
        expect(updateFilter).toHaveBeenCalledWith({
            price_from: '100',
            price_to: '',
            type: '',
            guests: '',
            checkin: '',
            checkout: '',
        });

    });


});
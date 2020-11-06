import React, {useState} from 'react';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale,  } from  "react-datepicker";
import es from 'date-fns/locale/es';

//https://reactdatepicker.com/
//https://github.com/Hacker0x01/react-datepicker

import StorageManager from "components/StorageManager/StorageManager";

export const CheckInPicker = (props) => {
    registerLocale('es', es)
    //const [checkin, setCheckinDate] = useState(props.date);
    //console.log('CheckInPicker: ',checkin,' props: ',props);
    return (
      <DatePicker
          //inline
          locale="es"
          minDate={new Date()}
          dateFormat="dd/MM/yyyy"
          //selected={checkin} 
          selected={StorageManager.getCheckin()}
          onChange={date => { props.handle(date); StorageManager.saveCheckin(date)}}
          excludeDates={props.excludeDates}
          //highlightDates={props.excludeDates}
          //highlightDates={highlightWithRanges}
          />
    );
  }
  
  
export const CheckOutPicker = (props) => {
    //const [checkout, setCheckoutDate] = useState(props.date);
    //console.log('CheckOutPicker: ',checkout,' props: ',props);
    return (
      <DatePicker 
          //inline
          locale="es"
          minDate={new Date()}
          dateFormat="dd/MM/yyyy"
          selected={StorageManager.getCheckout()} 
          onChange={date => { props.handle(date); StorageManager.saveCheckout(date)}}
          excludeDates={props.excludeDates}
          />
    );
  }
  
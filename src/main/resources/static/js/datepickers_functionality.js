var date = new Date();
var disabledDate = ['2020-10-1', '2-10-2020','3-10-2020'];

$("#checkin").datepicker({
    format: "dd-mm-yyyy",
    todayBtn: true,
    autoclose: true,
    startDate: date,
    disabledDates: disabledDate
})
    .on("changeDate", function(e) {
        var checkInDate = e.date;
        var checkOut = $("#checkout");
        checkInDate.setDate(checkInDate.getDate() + 1);
        checkOut.datepicker("setStartDate", checkInDate);
        checkOut.datepicker("setDate", checkInDate).focus();
    });

$("#checkout").datepicker({
    format: "dd-mm-yyyy",
    todayBtn: true,
    autoclose: true
});
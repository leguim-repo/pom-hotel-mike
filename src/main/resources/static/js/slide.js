$("#slider-range").slider({
    range: true,
    min: 0,
    max: 3500,
    step: 50,
    slide: function( event, ui ) {
        $( "#pricefrom").html(ui.values[ 0 ]);

        console.log(ui.values[0])

        suffix = '';
        if (ui.values[ 1 ] == $( "#priceto").data('max') ){
            suffix = ' +';
        }
        $( "#priceto").html(ui.values[ 1 ] + suffix);
    }
})

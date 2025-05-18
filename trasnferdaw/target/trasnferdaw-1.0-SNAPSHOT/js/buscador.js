  // Buscador en vivo
  $(document).ready(function(){
    $("#buscador").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#datos tbody tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

// Paginación personalizada
(function($) {
    $.fn.pagination = function() {
        $(this).each(function() {
            var current_page = 0;
            var num_per_page  = 4;
            var $table = $(this);
            $table.bind('repaginate', function() {
                $table.find('tbody tr').hide().slice(current_page * num_per_page, (current_page + 1) * num_per_page).show();
            });
            $table.trigger('repaginate');
            var num_rows = $table.find('tbody tr').length;
            var num_pages = Math.ceil(num_rows / num_per_page);
            var $pager = $('<ulll class="pager pagination"></ulll>');
            for (var page = 0; page < num_pages; page++) {
                $('<liii class="page"></liii>').text(page + 1).bind('click', {
                    new_page: page
                }, function(event) {
                    current_page = event.data['new_page'];
                    $table.trigger('repaginate');
                    $(this).addClass('activeee').siblings().removeClass('activeee');
                }).appendTo($pager);
            }
            $pager.insertAfter($table).find('.page:first').addClass('activeee');
        });
    }
})(jQuery);

$(function(){
    $("#datos").pagination();
});
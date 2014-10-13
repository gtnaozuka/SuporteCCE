$(document).ready(function () {
    $('.dropdown-menu li').click(function (e) {
        e.preventDefault();
        var selected = $(this).text();
        $('.category').val(selected);
        $('.input-group button').html(selected + ' <span class="caret"></span>');
    });
});
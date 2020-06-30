/**
 *
 */

$(document).ready(function () {
    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.dModal #d_id').val(user.id);
            $('.dModal #d_firstname').val(user.firstname);
            $('.dModal #d_lastname').val(user.lastname);
            $('.dModal #d_age').val(user.age);
            $('.dModal #d_email').val(user.email);
        });

        $('.dModal #deleteModal').modal();
    });
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('.eModal #e_id').val(user.id);
            $('.eModal #e_firstname').val(user.firstname);
            $('.eModal #e_lastname').val(user.lastname);
            $('.eModal #e_age').val(user.age);
            $('.eModal #e_email').val(user.email);
        });

        $('.eModal #editModal').modal();
    });
});
$('form').each(function () {
    $(this).find('div.form-group').each(function () {
        if ($(this).find('span').length > 0) {
            $(this).addClass('has-error');
        }
    });
});
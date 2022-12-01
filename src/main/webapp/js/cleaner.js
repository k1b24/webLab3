function resetInput() {
    let field = $('#x_value');
    $('#x' + field.val()).removeClass('selected');
    field.val("");
    field = $('#y_value');
    $('#y' + field.val()).removeClass('selected');
    field.val("");
}
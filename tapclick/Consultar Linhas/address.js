function address() {
  var input, filter, span, a, i, txtValue;
  input = document.getElementById("explore-search");
  filter = input.value.toUpperCase();
  span = document.getElementById("line-detail");
  a = span.getElementsByTagName("span");

  for (i = 0; i < span.length; i++) {
    a = span[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      span[i].style.display = "";
    } else {
      span[i].style.display = "none";
    }
  }
}

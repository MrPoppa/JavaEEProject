function disabledDays(date)
{
  var day = date.getDay();
  return [(day !== 2 && day !== 3 && day !== 4 && day !== 5 && day !== 6 && day !== 0), ''];
}
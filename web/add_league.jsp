<html>
<head>
<title>Duke's Soccer League: Add a New League</title>
</head>
<body>

<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='400'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
  <td><h3>Duke's Soccer League: Add a New League</h3></td>
</tr>
</table>

<p>
This form allows you to create a new soccer league.
</p>

<form action='AddLeagueServlet' method='POST'>
Year: <input type='text' name='year' /> <br/><br/>
Season: <select name='season'>
          <option value='UNKNOWN'>select...</option>
          <option value='Spring'>Spring</option>
          <option value='Summer'>Summer</option>
          <option value='Fall'>Fall</option>
          <option value='Winter'>Winter</option>
        </select> <br/><br/>
Title: <input type='text' name='title' /> <br/><br/>
<input type='submit' value='Add League' />
</form>

</body>
</html>
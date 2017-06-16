<!-- Filename: FeerReader.jsp -->
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
	<TITLE>Feed Reader in JSTL</TITLE>
	<STYLE>
		table 
             {border: 2px ridge ; width: 500px}
		#feed .title
             {font-family: Arial; font-weight: bold; font-size: 18px}
		#feed .label
             {font-family: Tahoma; font-weight: bold; font-size: 11px}
		#feed td 
             {font-family: Tahoma; font-size: 11px}
	</style>
</HEAD>
<BODY>
	<form>
		<input type="text" name="feedURL" 
			value="http://news.google.com/?output=rss" /> 
		<input type="submit" value="Display"/>
	</form>

	<c:if test="${param.feedURL != null}">
		
	Feed URL: ${param.feedURL}

  	<c:import var="xmlContent" url="${param.feedURL}"/>
	
	<x:parse var="doc" xml="${xmlContent}"/>
    	
    	
    <table class="content-table"" id="feed">
    <tr class="profile_odd">
    	<td align="center" colspan="2">  
    		<span class="title">
               <x:out select="$doc/rss/channel/title" />
    		</span> 
    	</td>
    </tr>
     <x:forEach var="story" 
                select="$doc/rss/channel/item" varStatus="status">
     	<tr>
     		<td colspan="2"> <hr/> </td>
     	</tr>
        <tr class="profile_even">
          <td class="label">Topic</td>
          <td> <x:out select="title" /> </td>	
        </tr>
        <tr class="profile_even">
          <td class="label">Published Date</td>
          <td> <x:out select="pubDate" /> </td>	
        </tr>
        <tr class="profile_even">
          <td class="label">Category</td>
          <td> <x:out select="category" /> </td>	
        </tr>
        <tr class="" valign="top">
        	<td class="label">Description</td>
        	<td><x:out select="description" escapeXml="false"/></td>
        </tr>
      </x:forEach>
    </table>
	</c:if>
</BODY>
</HTML>


<html>
    <head>
        <%
            def entity = request.getAttribute('entity')
        %>

        <title>Spock Web Console - ${entity?.title ?: 'Untitled Script'}</title>

        <link rel="stylesheet" type="text/css" href="/css/redmond/jquery-ui-1.7.1.custom.css"/>
        <link rel="stylesheet" type="text/css" href="/css/main.css"/>

        <link type="text/css" rel="stylesheet" href="/css/shCore.css"/>
        <link type="text/css" rel="stylesheet" href="/css/shThemeDefault.css"/>

        <script src="js/jquery-1.3.2.min.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>

        <script language="javascript" src="/js/shCore.js"></script>
        <script language="javascript" src="/js/shBrushGroovy.js"></script>
        <script language="javascript" src="/js/shBrushXml.js"></script>

        <script type="text/javascript">
        	SyntaxHighlighter.config.clipboardSwf = '/flash/clipboard.swf';
        	SyntaxHighlighter.defaults['gutter'] = false;
        	SyntaxHighlighter.all();
        </script>

        <script type="text/javascript" charset="utf-8" src="http://bit.ly/javascript-api.js?version=latest&login=glaforge&apiKey=R_c6e14f0ec7fd7c31296c8d394cfbe929"></script>
        <script type="text/javascript" charset="utf-8" src="http://s.bit.ly/TweetAndTrack.js?v=1.01"></script>
    </head>

    <body>
        <script src="js/view.js" type="text/javascript"></script>
    
        <h1><a href="/">Spock Web Console</a></h1>

        <div id="shareThis">
            <table cellspacing="20">
                <tr>
                    <td>
                        <a id="embedLink" href="#">
                            <table>
                                <tr>
                                    <td><img src="/images/puzzle.png" alt="embed in your blog" align="left" border="0"></td>
                                    <td><i>Embed<br/>This<br/>Script</i></td>
                                </tr>
                            </table>
                        </a>
                    </td>
                    <td>
                        <a href="#" onclick="return TweetAndTrack.open(this, 'http://meetspock.appspot.com/view.groovy?id=${entity.key.id}');">
                            <span style="display:none;">${entity.title} (via #spockwebconsole)</span>
                            <table>
                                <tr>
                                    <td><img src="/images/twitter.png" alt="tweet this script" align="left" border="0"></td>
                                    <td><i>Tweet<br/>This<br/>Script</i></td>
                                </tr>
                            </table>
                        </a>
                    </td>
                </tr>
            </table>
            <div id="embedText" title="Embed This Script">
                <p>To embed this script in your site, just drop the content below where you want to embed it.</p>
                <textarea cols="55" rows="14">
&lt;script&gt;
    // The ID of this script
    gc_id = ${entity.key.id};

    // The iframe's width
    gc_width = 300;

    // The iframe's height
    gc_height = 100;
&lt;/script&gt;
&lt;script language="javascript"
             src="http://meetspock.appspot.com/js/embed.js"&gt;
&lt;/script&gt;
                </textarea>
            </div>
        </div>

        <h2>${entity?.title ?: 'Untitled Script'}</h2>
        <div id="publishedby">
            <img src="/images/date.png" align="top">
            Published ${new com.ocpsoft.pretty.time.PrettyTime().format(entity.dateCreated)}
            by
            <%
                if (entity.author && entity.author != 'Anonymous') {
            %>
                <img src="/images/user.png" align="top">
                <a href="/recentscripts.gtpl?author=${entity.author}&limit=40">${entity.author}</a>
            <%
                } else {
            %>
                Anonymous
            <%
                }
                if (entity?.tags) {
            %>
                with tags
            <%
                    entity.tags.each { tag ->
            %>
                <img src="/images/tag_blue.png" align="top">
                <a href="/recentscripts.gtpl?tag=${tag}&limit=40">${tag}</a>
            <%
                    }
                }
            %>
        </div>

        <div id="actionsBreadcrumb">
            <span class="actionsBreadcrumbHead">Actions &nbsp;&#x27A4;</span>
            <span class="actionsBreadcrumbChild"><a href="/?id=${entity.key.id}">Edit In Console</a></span>
            <span class="actionsBreadcrumbChild"><a href="/">Back To Console</a></span>
            <span class="actionsBreadcrumbChild" id="toggleLineNumbers"><a href="javascript:void(0)">Show/Hide Line Numbers</a></span>
            <span class="actionsBreadcrumbLastChild"><a href="/recentscripts.gtpl?limit=40">View Recent Scripts</a></span>
        </div>

        <pre class="brush:groovy">${entity.script.value.replaceAll('<', '&lt;')}</pre>

        <div id="commentsArea">
            <script>
                var idcomments_acct = 'ffac2056f3a0f603b8799858d3af8299';
                var idcomments_post_id;
                var idcomments_post_url;
                </script>
                <span id="IDCommentsPostTitle" style="display:none"></span>
            <script type='text/javascript' src='http://www.intensedebate.com/js/genericCommentWrapperV2.js'></script>
        </div>

        <% include '/WEB-INF/includes/about.gtpl' %>
    </body>
</html>
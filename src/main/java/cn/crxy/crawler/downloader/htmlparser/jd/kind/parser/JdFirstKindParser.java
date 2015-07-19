package cn.crxy.crawler.downloader.htmlparser.jd.kind.parser;

import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.visitors.NodeVisitor;

public class JdFirstKindParser {

	public static void main(String[] args) {
		try {
			new JdFirstKindParser().parser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parser() throws Exception{
		Parser parser = new Parser(new URL("http://www.jd.com/allSort.aspx").openConnection());
		Node[] nodeArray = parser.extractAllNodesThatMatch(new CssSelectorNodeFilter("div[id='allsort'] div[class='m']>div[class='mt']>h2>a")).toNodeArray();
		for (Node node : nodeArray) {
			node.accept(new NodeVisitor(true) {
				@Override
				public void visitTag(Tag tag) {
					String name = tag.toPlainTextString();
					String url = tag.getAttribute("href");
					System.out.println(name+"\t"+url);
				}
			});
		}
	}

}

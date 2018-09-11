package id.glanexlabs.news.app.model;

/*berita data model
* selasa 8 mei 2018
*/
public class BeritaModel
{
	private String title;
	private String publishedAt;
	private String url;
	private String urlToImage;
	
	
	public String getTitle(){
		return title;
	}
	public String getPublishedAt(){
		return publishedAt;
	}
	public String getUrl(){
		return url;
	}
	public String getUrlToImage(){
		return urlToImage;
	}
	
	public BeritaModel(String title, String publishedAt, String url, String urlToImage){
		this.title = title;
		this.publishedAt = publishedAt;
		this.url = url;
		this.urlToImage = urlToImage;
	}
}

package org.jreddit.webservice.jredditwebservice.model;
/**
 * Author: Jacob Hong
 */
import java.util.List;
import java.util.Map;

public class Multi {
	private String can_edit;
	private String copied_from;
	private String created;
	private String created_utc;
	private String description_md;
	private String description_html;
	private	String display_name;
	private String icon_name;
	private String key_color;
	private String name;
	private String path;
	private String icon_url;
	private String visibility;
	private String weighting_scheme;	
	private List<Map<String, String>> subreddits;
	
	public String getCan_edit() {
		return can_edit;
	}
	public void setCan_edit(String can_edit) {
		this.can_edit = can_edit;
	}
	public String getCopied_from() {
		return copied_from;
	}
	public void setCopied_from(String copied_from) {
		this.copied_from = copied_from;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreated_utc() {
		return created_utc;
	}
	public void setCreated_utc(String created_utc) {
		this.created_utc = created_utc;
	}
	public String getDescription_md() {
		return description_md;
	}
	public void setDescription_md(String description_md) {
		this.description_md = description_md;
	}
	public String getDescription_html() {
		return description_html;
	}
	public void setDescription_html(String description_html) {
		this.description_html = description_html;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getIcon_name() {
		return icon_name;
	}
	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}
	public String getKey_color() {
		return key_color;
	}
	public void setKey_color(String key_color) {
		this.key_color = key_color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getWeighting_scheme() {
		return weighting_scheme;
	}
	public void setWeighting_scheme(String weighting_scheme) {
		this.weighting_scheme = weighting_scheme;
	}
	public List<Map<String, String>> getSubreddits() {
		return subreddits;
	}
	public void setSubreddits(List<Map<String, String>> subreddits) {
		this.subreddits = subreddits;
	}
}

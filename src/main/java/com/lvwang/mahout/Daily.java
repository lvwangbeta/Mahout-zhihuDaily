package com.lvwang.mahout;

import java.util.List;

public class Daily {
	private String date;
	private List<Single> stories;
	
	class Single {
		private String[] images;
		private int type;
		private int id;
		private String ga_prefix;
		private String title;
		public String[] getImages() {
			return images;
		}

		public void setImages(String[] images) {
			this.images = images;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getGa_prefix() {
			return ga_prefix;
		}

		public void setGa_prefix(String ga_prefix) {
			this.ga_prefix = ga_prefix;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Single> getStories() {
		return stories;
	}

	public void setStories(List<Single> stories) {
		this.stories = stories;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer(date+" \n");
		for(int i=0;i<stories.size(); i++) {
			Single single = stories.get(i);
			buffer.append("id:"+single.getId() + "\n");
		}
		return buffer.toString();
	}
}

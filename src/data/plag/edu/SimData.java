package data.plag.edu;

public class SimData  implements Comparable<SimData> {
  String file1;
  String file2;
  float similar;
  String url;
public String getFile1() {
	return file1;
}
public void setFile1(String file1) {
	this.file1 = file1;
}
public String getFile2() {
	return file2;
}
public void setFile2(String file2) {
	this.file2 = file2;
}
public float getSimilar() {
	return similar;
}
public void setSimilar(float similar) {
	this.similar = similar;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
@Override
public int compareTo(SimData arg0) {
	// TODO Auto-generated method stub
	if(this.similar>arg0.getSimilar())
		return 1;
	else if(this.similar<arg0.getSimilar()){
		return -1;
	}else
	    return 0;
}
}

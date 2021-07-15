package jp.toufu3.ninech.http;

public class Path {
    private final String[] rawPath;

    public Path(String path){
        this.rawPath = path.split("/");
    }

    public boolean isMatch(String requestURL){
        String[] request = requestURL.split("/");
        if(this.rawPath.length != request.length){
            return false;
        }
        for (int i = 0; i < request.length; i++) {
            if(request[i].charAt(0) == ':'){
                continue;
            }
            if(!request[i].equalsIgnoreCase(this.rawPath[i])){
                return false;
            }
        }
        return true;
    }
}

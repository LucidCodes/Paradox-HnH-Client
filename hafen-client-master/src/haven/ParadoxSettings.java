package haven;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ParadoxSettings {
	public boolean ReplaceFonts = false;
	private static final Path path = Paths.get("Settings.txt").toAbsolutePath();

	public ParadoxSettings(){
		File Settings = new File(path.toString());
		if(!Settings.exists()){
			try{
				Settings.createNewFile();
				SetFonts(false);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		ReplaceFonts = CheckFonts();
	}
	
	public static Font getFont(){
		try{
		List<String> lines = Files.readAllLines(path);
		if(lines.toArray()[0].equals("true")){
			return new Font("Sans", Font.PLAIN, 10);
		}else
			return Resource.local().loadwait("ui/fraktur").layer(Resource.Font.class).font;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean CheckFonts(){
		try{
		List<String> lines = Files.readAllLines(path);
		if(lines.toArray()[0].equals("true")){
			return true;
		}else
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void SetFonts(boolean replace){
		try {
			Files.write(path, String.valueOf(replace).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

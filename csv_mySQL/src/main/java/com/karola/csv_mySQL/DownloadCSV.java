package com.karola.csv_mySQL;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;

public class DownloadCSV implements Tasklet, InitializingBean{

	private String urlPart;
	int i = 1;
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
			String name ="src/main/resources/csv/file"+ geturlPart()+".csv"; 
			
			String urlName = "http://stooq.com/q/l/?s=" + geturlPart()+ "&f=sd2t2ohlcv&h&e=csv";
		 String fileName = name ; //The file that will be saved on your computer
		 URL link = new URL(urlName); //The file that you want to download
		 System.out.println("Walor: " + geturlPart());
    //Code to download
		 InputStream in = new BufferedInputStream(link.openStream());
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 byte[] buf = new byte[1024];
		 int n = 0;
		 while (-1!=(n=in.read(buf)))
		 {
		    out.write(buf, 0, n);
		 }
		 out.close();
		 in.close();
		 byte[] response = out.toByteArray();
		 
		 FileOutputStream fos = new FileOutputStream(fileName);
		 fos.write(response);
		 fos.close();
     //End download code
		 
		 System.out.println("Download completed");
		
		return null;
	}
	
	public void seturlPart(String urlPart){
		
		this.urlPart = urlPart;
	}
	
	public String geturlPart(){
		return urlPart;
	}


}

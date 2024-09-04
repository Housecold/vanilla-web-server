package com.codeforall.online;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 8095;
    private static final String PATH_ROOT = "src/main/www";

    
    //Initiate a new server
    public static void newServer() {
        System.out.println(">>>Awaiting connection from client...");

        try {
            ServerSocket serverSocket = new ServerSocket(port); //Create a server socket to listen on the specified port
            System.out.println(">>>Server listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); //Accept incoming client connections
                System.out.println(">>>Connection established with " + clientSocket.getInetAddress());

                handleRequest(clientSocket); //Handle the client's request
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Method to handle the request from the client
    public static void handleRequest(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //Read client input
            OutputStream out = clientSocket.getOutputStream(); //Output stream for sending response

            String request = in.readLine(); //Read the request line from the client

            //Check if the request is a valid GET request
            if (request == null || !request.startsWith("GET")) {
                sendErrorResponse(out, 400, "Bad Request", "Bad Request".getBytes()); //Send a "400 Bad Request" response if the request is invalid
                return;
            }

            String[] requestParts = request.split(" ");
            String filePath = requestParts[1];
            if (filePath.equals("/")) {
                filePath = "/index.html"; //Default to "index.html" if the root path is requested
            }
            File file = new File(PATH_ROOT, filePath); //Locate the requested file in the root directory

            //Check if the file exists and is not a directory
            if (file.exists() && !file.isDirectory()) {
                byte[] content = readFile(file); //Read the file content
                sendResponse(out, "HTTP/1.1 200 OK", getContent(filePath), content); //Send a "200 OK" response with the file content
            } else {
                File notFounFile = new File(PATH_ROOT, "404.html"); //Locate the "404.html" file for not found response
                byte[] content = readFile(notFounFile);
                sendResponse(out, "HTTP/1.1 404 File Not Found", "text/html", content); //Send a "404 Not Found" response
            }

            in.close();//Close the input stream
            out.close(); //Close the output stream

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    //Method to send the response from the server
    public static void sendResponse(OutputStream out, String status, String contentType, byte[] content) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println(status); //Send the status line
        writer.println("Content: " + contentType); //Send the content type header
        writer.println(); //Send a blank line to indicate the end of the headers
        writer.flush();

        out.write(content); //Write the response content
        out.flush();
    }


    //Method to send the ERROR message from the server
    public static void sendErrorResponse(OutputStream out, int statusCode, String statusText, byte[] content) throws IOException {
        sendResponse(out, "HTTP/1.1" + statusCode + " " + statusText, "text/plain", content); //Send an error response
    }


    //Method to read the file content and return it as a byte array
    private static byte[] readFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file); //Open the file input stream
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); //Create a byte array output stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead); //Read the file content into the buffer and write to the output stream
            }
            return bos.toByteArray(); //Return the file content as a byte array
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    //Method to get the content from the server
    private static String getContent(String filePath) {
        if (filePath.endsWith(".html")) {
            return "text/html";
        } else if (filePath.endsWith(".png")) {
            return "image/png";
        } else if (filePath.endsWith(".ico")) {
            return "image/x-icon";
        }
        return filePath; //Default content type for unknown file types
    }
}

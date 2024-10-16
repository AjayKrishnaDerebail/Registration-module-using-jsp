This is a simple project built using JSP.

Registration module home page.
![image](https://github.com/user-attachments/assets/ab04812e-fe23-452e-8d27-8c8ae5b30aec)

Successful registration

![image](https://github.com/user-attachments/assets/9ad7d184-6083-4e16-aa7d-53277cc13449)

Unsuccessful registration

![image](https://github.com/user-attachments/assets/544cfb2e-c244-4de4-99c4-b26acac28c75)

<p align="justify">

<b>Why Upload the File to the Servlet if It's Saved in the Database?</b>

While the file name (or a reference to the file) is saved in the database, the actual file content is not saved in the database itself (unless you're explicitly storing file data in a BLOB field, which doesn't seem to be the case here). Here’s why uploading the file to the server is necessary:

File path storage in the database: Typically, only the file name or file path is stored in the database, not the actual file content. The path helps to reference where the file is located on the server.

File storage on the server: The actual file data is saved in a physical location on the server (e.g., inside the images directory). This enables the application to retrieve or serve the file later, such as displaying an image or allowing users to download a document.


Separation of concerns:

- **Database**: It's considered good practice to store metadata about the file, such as its name, type, or path, in the database.
- **File system**: The actual binary data (the file) is stored on the server’s file system. Storing large files directly in the database can cause performance issues, which is why the file system is commonly used instead.
- **Ease of retrieval**: Once the file is uploaded to the server, it becomes easily accessible through its URL (e.g., http://yourdomain.com/images/filename.jpg). If the file were stored as binary data in the database, additional steps would be needed to convert it back into a file when requested.

In summary:
Database stores metadata (like file name and possibly file size).
The server stores the actual file (in the file system).
This separation improves performance, maintainability, and scalability, and makes handling files (like images, PDFs, etc.) simpler for the application.
</p>

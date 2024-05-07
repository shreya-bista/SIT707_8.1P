package web.handler;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class RegistrationServletTest {

    private RegistrationServlet registrationServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private PrintWriter writer;
    private ArgumentCaptor<String> captor;
    
    
    @Before
    public void setUp() {
        registrationServlet = new RegistrationServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);
        captor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    public void testValidRegistration() throws ServletException, IOException {
        // Mock request parameters
        when(request.getParameter("fname")).thenReturn("John");
        when(request.getParameter("lname")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn("john.doe@example.com");
        when(request.getParameter("dob")).thenReturn("2000-01-01");
        when(response.getWriter()).thenReturn(writer);

        // Invoke the doPost method
        registrationServlet.doPost(request, response);

        // Verify that the response status is OK
        verify(response).setStatus(HttpServletResponse.SC_OK);
        
        // Verify that the content type is set to "application/json"
        verify(response).setContentType("application/json");

        // Verify that a JSON response is printed
        verify(response.getWriter()).println("{\"status\": \"ok\"}");
    }

   
}

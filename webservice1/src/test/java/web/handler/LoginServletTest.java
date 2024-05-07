package web.handler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class LoginServletTest {

    private LoginServlet loginServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter writer;
    private ArgumentCaptor<String> captor;

    @Before
    public void setUp() {
        loginServlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);
        captor = ArgumentCaptor.forClass(String.class);
        
    }
  
    @Test
    public void testLoginSuccess() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("shreya");
        when(request.getParameter("passwd")).thenReturn("shreya_pass");
        when(request.getParameter("dob")).thenReturn("2024-01-05");
        when(response.getWriter()).thenReturn(writer);
        loginServlet.doPost(request, response);
        
       
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>success</title></head><h2 >Login status: <span name=\"status\">success</span></h2></html>";
        //System.out.println(captor.getValue());
        //System.out.println(expectedResponse);
        assertEquals(expectedResponse, captor.getValue());
        
    }

    @Test
    public void testLoginFail() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("passwd")).thenReturn("wrongpass");
        when(request.getParameter("dob")).thenReturn("01/01/2000");
        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";

        assertEquals(expectedResponse, captor.getValue());
    }

    // Add more test cases for edge cases and boundary conditions...

    @Test
    public void testEmptyUsernameAndPassword() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("passwd")).thenReturn("");
        when(request.getParameter("dob")).thenReturn("01/01/2000");
        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";
        assertEquals(expectedResponse, captor.getValue());

    }

    @Test
    public void testEmptyDOB() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("passwd")).thenReturn("pass");
        when(request.getParameter("dob")).thenReturn("");
        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";
        assertEquals(expectedResponse, captor.getValue());

    }

    @Test
    public void testInvalidDOBFormat() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("passwd")).thenReturn("pass");
        when(request.getParameter("dob")).thenReturn("01-01-2000");

        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";
        assertEquals(expectedResponse, captor.getValue());

    }

    @Test
    public void testInvalidUsernameOrPassword() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("invaliduser");
        when(request.getParameter("passwd")).thenReturn("invalidpass");
        when(request.getParameter("dob")).thenReturn("01/01/2000");
        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";
        assertEquals(expectedResponse, captor.getValue());

    }

    @Test
    public void testNullParameters() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn(null);
        when(request.getParameter("passwd")).thenReturn(null);
        when(request.getParameter("dob")).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";
        assertEquals(expectedResponse, captor.getValue());

    }

    @Test
    public void testValidUsernamePasswordWrongDOB() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("passwd")).thenReturn("pass");
        when(request.getParameter("dob")).thenReturn("02/02/2000");
        when(response.getWriter()).thenReturn(writer);

        loginServlet.doPost(request, response);
        verify(writer).println(captor.capture());

        String expectedResponse = "<html><head><title>fail</title></head><h2 >Login status: <span name=\"status\">fail</span></h2></html>";
        assertEquals(expectedResponse, captor.getValue());

    }
}

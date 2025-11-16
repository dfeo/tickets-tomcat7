<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tickets</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css">
    <style>
        body { margin-top: 20px; }
        .delete-btn { background: #e74c3c; border-color: #e74c3c; }
        .delete-btn:hover { background: #c0392b; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tickets</h1>

        <div class="row">
            <div class="six columns">
                <h2>Add New Ticket</h2>
                <form action="tickets" method="post">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" class="u-full-width" required>

                    <label for="description">Description</label>
                    <textarea id="description" name="description" class="u-full-width" required></textarea>

                    <label for="status">Status</label>
                    <select id="status" name="status" class="u-full-width">
                        <option value="open">Open</option>
                        <option value="closed">Closed</option>
                    </select>

                    <input type="submit" value="Add Ticket" class="button-primary">
                </form>
            </div>
            <div class="six columns">
                <h2>All Tickets</h2>
                <table class="u-full-width">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ticket" items="${tickets}">
                            <tr>
                                <td>${ticket.id}</td>
                                <td>${ticket.title}</td>
                                <td>${ticket.description}</td>
                                <td>${ticket.status}</td>
                                <td>
                                    <a href="tickets?action=edit&id=${ticket.id}" class="button">Edit</a>
                                    <form action="tickets" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${ticket.id}">
                                        <input type="submit" value="Delete" class="button delete-btn" onclick="return confirm('Are you sure?')">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
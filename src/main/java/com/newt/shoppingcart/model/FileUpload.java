/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newt.shoppingcart.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "file_upload")
@XmlRootElement
public class FileUpload implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_upload_id")
    private Integer fileUploadId;
    @Column(name = "file_name")
    private String filename;
    @Column(name = "file_size")
    private Integer filesize;
    @Lob
    @Column(name = "file_content")
    private Blob fileContent;
    
    @Column(name = "product_id")
    private int productId;
 
    public FileUpload() {
    }

    public FileUpload(Integer fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    public Integer getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(Integer fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public Blob getFileContent() {
        return fileContent;
    }

    public void setFileContent(Blob fileContent) {
        this.fileContent = fileContent;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileUploadId != null ? fileUploadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileUpload)) {
            return false;
        }
        FileUpload other = (FileUpload) object;
        if ((this.fileUploadId == null && other.fileUploadId != null) || (this.fileUploadId != null && !this.fileUploadId.equals(other.fileUploadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.newt.model.FileUpload[ fileUploadId=" + fileUploadId + " ]";
    }
    
}
